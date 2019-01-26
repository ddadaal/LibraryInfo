package libraryinfo.repository

import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDateTime

class UserManagementTest {

    @Test
    @BeforeEach
    fun insertInitialData() {
        InitData.insertInitialData()
    }

}