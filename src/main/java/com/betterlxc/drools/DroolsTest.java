package com.betterlxc.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class DroolsTest {


    private static String getRule() {
        String s = "" +
            "package org.drools.example.api.kiefilesystem \n\n" +
            "import org.drools.example.api.kiefilesystem.Message \n\n" +
            "global java.io.PrintStream out \n" +
            "rule \"rule 1\" when \n" +
            "    m : Message( ) \n" +
            "then \n" +
            "    out.println( m.getName() + \": \" +  m.getText() ); \n" +
            "end \n" +
            "rule \"rule 2\" when \n" +
            "    Message( text == \"Hello, HAL. Do you read me, HAL?\" ) \n" +
            "then \n" +
            "    insert( new Message(\"HAL\", \"Dave. I read you.\" ) ); \n" +
            "end";

        return s;
    }

    @Test
    public void test1() {
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        kfs.write("/Users/lxc/Github/Java/demo/src/main/resources/HAL5.drl", getRule());

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.

        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());

        KieSession kSession = kContainer.newKieSession();
        kSession.setGlobal("out", System.out);

        kSession.insert(new Message("Dave", "Hello, HAL. Do you read me, HAL?"));
        kSession.fireAllRules();
    }

}
