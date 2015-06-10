package org.brunocunha.jobstats.elasticsearch;

import java.io.IOException;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.brunocunha.jobstats.model.Position;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * ElasticSearch Helper for Jobstats
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Log4j
public class ElasticSearchHelper {

	public static Client buildClient() {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "elasticsearch").build();

		@SuppressWarnings("resource")
		Client client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"localhost", 9300));

		return client;
	}

	/**
	 * Effectively index the given List in ElasticSearch
	 * @param positions
	 * @throws IOException
	 */
	public static void index(List<Position> positions) throws IOException {
		Client client = buildClient();
		BulkRequestBuilder bulk = client.prepareBulk();

		Gson gson = new GsonBuilder().serializeNulls().create();

		for (Position position : positions) {

			log.info("Adding " + position.getOriginId() + " to bulk request.");
			bulk.add(client.prepareIndex("job", "doc", position.getOriginId())
					.setSource(gson.toJson(position)));
		}

		executeBulk(bulk);
	}

	/**
	 * Executes the bulk request
	 * @param bulk
	 */
	private static void executeBulk(BulkRequestBuilder bulk) {
		log.info("Executing bulk...");
		ListenableActionFuture<BulkResponse> futures = bulk.execute();
		BulkResponse response = futures.actionGet();

		log.info("Response: " + response.getTookInMillis());
	}

}
