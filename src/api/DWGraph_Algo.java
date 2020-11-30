package ex2.src.api;

import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {

    private directed_weighted_graph dw_graph;

    public DWGraph_Algo() {
        dw_graph = new DWGraph_DS();
    }


    @Override
    public void init(directed_weighted_graph g) {
        this.dw_graph = g;
    }

    @Override
    public directed_weighted_graph getGraph() {
        return dw_graph;
    }

    @Override
    public directed_weighted_graph copy() {
        DWGraph_DS ans = new DWGraph_DS((DWGraph_DS) dw_graph);
        return ans;
    }

    @Override
    public boolean isConnected() {
        if (dw_graph.getV().size() <= 1) return true;
        if (dw_graph.edgeSize() < dw_graph.nodeSize() - 1) return false;

        resetTag(dw_graph, 0);
        int counter = 1;

        Queue<node_data> checklist = new LinkedList<>();

        Collection<node_data> points = dw_graph.getV();
        Iterator<node_data> st = points.iterator();
        node_data head = st.next();
        head.setTag(1);
        checklist.add(head);

        while (!checklist.isEmpty()) {
            node_data currentNode = checklist.poll();

            Collection<node_data> nei = ((NodeData) currentNode).getNi();
            Iterator<node_data> it = nei.iterator();
            while (it.hasNext()) {
                node_data temp = it.next();
                if (temp.getTag() == 0) {
                    checklist.add(temp);
                    temp.setTag(1);
                    counter++;
                }
            }
        }
        if (counter == dw_graph.nodeSize()) {

            resetTag(dw_graph, 0);
            int counter1 = 1;

            Queue<node_data> checklist1 = new LinkedList<>();

            Collection<node_data> points1 = dw_graph.getV();
            Iterator<node_data> st1 = points1.iterator();
            head = st1.next();
            head.setTag(1);
            checklist1.add(head);

            while (!checklist1.isEmpty()) {
                node_data currentNode = checklist1.poll();

                Collection<node_data> parents = ((NodeData) currentNode).getParents();
                Iterator<node_data> it = parents.iterator();
                while (it.hasNext()) {
                    node_data temp = it.next();
                    if (temp.getTag() == 0) {
                        checklist1.add(temp);
                        temp.setTag(1);
                        counter1++;
                    }
                }
            }

            if (counter1 == dw_graph.nodeSize())
                return true;
        }
        return false;
    }

    public void resetTag(directed_weighted_graph g, int tag) {
        Collection<node_data> points = g.getV();
        Iterator<node_data> st = points.iterator();

        while (st.hasNext()) {
            node_data temp = st.next();
            temp.setTag(tag);
        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }


}
