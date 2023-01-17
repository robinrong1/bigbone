package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class ReportsMethodsTest {
    @Test
    fun getReports() {
        val client = MockClient.mock("reports.json")
        val reportsMethods = ReportsMethods(client)
        val pageable = reportsMethods.getReports().execute()
        val report = pageable.part.first()
        report.id shouldBeEqualTo "100"
        report.actionTaken shouldBeEqualTo "test"
    }

    @Test
    fun getReportsWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportsMethods = ReportsMethods(client)
            reportsMethods.getReports().execute()
        }
    }

    @Test
    fun fileReportWithException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val reportsMethods = ReportsMethods(client)
            reportsMethods.fileReport("10", "20", "test").execute()
        }
    }
}