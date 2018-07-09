package support

import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import groovy.json.JsonOutput

/**
 * Extracts all information gathered as JSON to a file which is later processed
 * by the plugin.
 */
class ReportListener implements ReportingListener {

    def report = [tests: [], reports: []]
    def reportDataFileName = "gebReportInfo.json"

//    void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
//        reportFiles.each {
//            printDebug("ATTACHMENT",it.absolutePath)
////            println "{\"ATTACHMENT\":\"$it.absolutePath\"}"
//        }
//    }


    void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {

        this.writeReportResource(reportState.browser.config.reportsDir.path.toString(),
                reportState.browser.reportGroup.toString(),
                reportState.browser.driver.currentUrl.toString(),
                reportState.browser.page.toString(),
                reportState.label.toString(),
                reportFiles
        )
    }

    void writeReportResource(String reportDir, String reportGroup, String currentUrl, String className, String label, List<File> reportFiles) {
        def out = new File(reportDir, reportDataFileName)
        def (testNum, reportNum, testLabel, reportLabel) = label.split('(?<!-)-(?!-)') // only split on single '-'
        //re-escape dashes
        testLabel = testLabel.replaceAll('--', '-')
        reportLabel = reportLabel.replaceAll('--', '-')

        def res = [
                spec: [
                        label: reportGroup,
                        test : [
                                num   : (testNum),
                                label : testLabel,
                                report: [
                                        time : new Date().time,
                                        num  : reportNum,
                                        label: reportLabel,
                                        url  : currentUrl,
                                        page : className,
                                        files: reportFiles.collect { it.absolutePath }
                                ]
                        ]
                ]
        ]

        out << JsonOutput.toJson(res) + "\n"
    }

}
