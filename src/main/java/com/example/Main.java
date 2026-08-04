package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;

import sootup.apk.frontend.ApkAnalysisInputLocation;
import sootup.apk.frontend.DexBodyInterceptors;
import sootup.core.graph.StmtGraph;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.model.SootClass;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSignature;
import sootup.java.bytecode.frontend.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.views.JavaView;

public class Main {

    public static void main(String[] args) {

        Path path = Paths.get("apks/test.apk");
        AnalysisInputLocation inputLocation = new ApkAnalysisInputLocation(path, "",
                DexBodyInterceptors.Default.bodyInterceptors());
        JavaView view = new JavaView(inputLocation);

        runIntraproceduralAnalysis(view);

        runInterproceduralAnalysis(view);


        // MethodSignature sig = view.getIdentifierFactory().getMethodSignature(...);
                        
        // dotFromFolder("...", sig);

    }

    private static void dotFromFolder(String folder, MethodSignature sig) {

        JavaClassPathAnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(folder);

        JavaView view = new JavaView(inputLocation);

        SootMethod method =
                view.getMethod(sig).orElseThrow();

        StmtGraph<?> cfg = method.getBody().getStmtGraph();

        DotExporter.export(cfg, ""+sig.getName()+".dot");
    }

    /**
     * Equivalent pedagogical role of old JTP.
     *
     * Method-by-method analysis.
     */
    private static void runIntraproceduralAnalysis(JavaView view) {

        System.out.println();
        System.out.println("========== INTRAPROCEDURAL ANALYSIS ==========");

        for (SootClass clazz : view.getClasses().toList()) {
            for (SootMethod method : clazz.getMethods()) {

            }
        }
    }

    /**
     * Equivalent pedagogical role of old WJTP.
     *
     * Whole-program CFG analysis.
     */
    private static void runInterproceduralAnalysis(JavaView view) {

        System.out.println();
        System.out.println("========== CFG ANALYSIS ==========");

    }
}