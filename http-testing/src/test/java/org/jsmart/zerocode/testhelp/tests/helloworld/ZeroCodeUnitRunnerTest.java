package org.jsmart.zerocode.testhelp.tests.helloworld;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.notification.RunNotifier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ZeroCodeUnitRunnerTest {

    ZeroCodeUnitRunner zeroCodeUnitRunner;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @TargetEnv("github_host.properties")
    public static class TinySmartJUnitRunnerExampleTester {

        @JsonTestCase("helloworld/hello_world_status_ok_assertions.json")
        @Test
        public void tinyTestCase2() throws Exception {
        }

//        @JsonTestCase("/mac-daddy") //<---- This one will be first in the list, alphabetically sorted
//        @Test
//        public void tinyTestCase1() throws Exception {
//        }
//
//        static String message = "none";
//        @Test
//        public void test1EqualTo1_pass() throws Exception {
//            message = "_pass";
//            assertThat(1, is(1));
//        }

    }

    @Before
    public void initializeRunner() throws Exception {
        zeroCodeUnitRunner = new ZeroCodeUnitRunner(TinySmartJUnitRunnerExampleTester.class);
    }

    @Test
    public void testWillReadTheAnnotationAndRunVia_BlockJunitRunner() throws Exception {
        assertThat(zeroCodeUnitRunner.getSmartTestCaseNames().size(), is(3));
        assertThat(zeroCodeUnitRunner.getSmartTestCaseNames().get(0), is("/mac-daddy"));
    }

    @Test
    public void testWillReadTheAnnotationAnd_Notify() throws Exception {
        zeroCodeUnitRunner = new ZeroCodeUnitRunner(TinySmartJUnitRunnerExampleTester.class);

        System.setProperty("zerocode.junit", "gen-smart-charts-csv-reports");
        zeroCodeUnitRunner.run(new RunNotifier());
        //assertThat(zeroCodeUnitRunner.getCurrentTestCase(), is("/abcd/path"));
    }

//    @Test
//    public void testPureJUnitExecution() throws Exception {
//        zeroCodeUnitRunner.run(new RunNotifier());
//        assertThat(zeroCodeUnitRunner.passed, is(true));
//        assertThat(TinySmartJUnitRunnerExampleTester.message, is("_pass"));
//    }

}