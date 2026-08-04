package com.example;

import sootup.core.graph.StmtGraph;
import sootup.core.jimple.common.stmt.Stmt;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class DotExporter {

    public static void export(StmtGraph<?> graph, String outputFile) {

        try (PrintWriter out = new PrintWriter(outputFile)) {

            out.println("digraph CFG {");

            Map<Stmt, String> ids = new HashMap<>();

            int i = 0;
            for (Stmt stmt : graph.getNodes()) {
                String id = "n" + i++;
                ids.put(stmt, id);

                String label = stmt.toString()
                        .replace("\"", "\\\"");

                out.printf("%s [label=\"%s\"];\n", id, label);
            }

            for (Stmt stmt : graph.getNodes()) {
                for (Stmt succ : graph.successors(stmt)) {
                    out.printf("%s -> %s;\n",
                            ids.get(stmt),
                            ids.get(succ));
                }
            }

            out.println("}");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}