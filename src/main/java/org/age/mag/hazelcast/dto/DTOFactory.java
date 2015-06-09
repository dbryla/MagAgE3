package org.age.mag.hazelcast.dto;

import java.util.List;

import org.age.mag.hazelcast.NodeInfo;
import org.age.services.identity.NodeType;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.UnmodifiableDirectedGraph;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Factory for creating simple representation of data in data transfer object.
 *
 */
public final class DTOFactory {
    private DTOFactory() {
    }

    /**
     * Create node data transfer object from given node info
     * 
     * @param node
     * @return object of dto with only important information
     */
    public static NodeDTO createNode(NodeInfo node) {
        NodeDTO dto = new NodeDTO();
        dto.setId(node.getId());
        dto.setType(node.getDescriptor().type().toString());
        if (!dto.type.equals(NodeType.SATELLITE.toString())) {
            dto.setWorkerState(node.getWorkerState().toString());
            List<Throwable> errors = node.getStatus().errors();
            dto.setErrors(errors.toArray(new Throwable[errors.size()]));
        }
        return dto;
    }

    /**
     * Create cluster data transfer object from given parameters.
     * 
     * @param clientName
     *            name of connected client
     * @param masterNode
     *            id of connected node
     * @param members
     *            list of connected addresses in format IP:PORT
     * @return simple representation of this information in one object
     */
    public static ClusterDTO createCluster(String clientName,
            String masterNode, List<String> members) {
        ClusterDTO dto = new ClusterDTO();
        dto.setClientName(clientName);
        dto.setMasterNode(masterNode);
        dto.setMembers(members.toArray(new String[members.size()]));
        return dto;
    }

    /**
     * Converts given graph to json form required by sigma.js
     * 
     * @param graph
     *            of topology
     * @return json representation of graph
     */
    public static String convertGraphToJson(
            UnmodifiableDirectedGraph<String, DefaultEdge> graph) {

        JSONArray vertexArray = new JSONArray();

        if (graph != null)
            graph.vertexSet().forEach(
                    vertex -> {
                        JSONObject obj = new JSONObject();
                        obj.put("id", vertex.toString());
                        obj.put("label", vertex.toString());
                        obj.put("x",
                                Math.round(100 * Math.cos(2
                                        * vertexArray.length() + 1 * Math.PI
                                        / graph.vertexSet().size())));
                        obj.put("y",
                                Math.round(100 * Math.sin(2
                                        * vertexArray.length() + 1 * Math.PI
                                        / graph.vertexSet().size())));
                        obj.put("size", 2);
                        vertexArray.put(obj);
                    });

        JSONArray edgeArray = new JSONArray();

        if (graph != null)
            graph.edgeSet().forEach(edge -> {
                JSONObject obj = new JSONObject();
                obj.put("id", "" + edgeArray.length());
                obj.put("source", graph.getEdgeSource(edge));
                obj.put("target", graph.getEdgeTarget(edge));
                edgeArray.put(obj);
            });

        JSONObject graphObj = new JSONObject();
        graphObj.put("nodes", vertexArray);
        graphObj.put("edges", edgeArray);

        return graphObj.toString();

    }

}
