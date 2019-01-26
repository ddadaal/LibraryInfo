package libraryinfo.repository

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.role.UserRole
import libraryinfo.domain.entity.user.userprivilege.AdminPrivilege
import libraryinfo.domain.entity.user.userprivilege.GraduatePrivilege
import libraryinfo.domain.entity.user.userprivilege.UndergraduatePrivilege
import libraryinfo.domain.entity.user.userprivilege.UserPrivilege
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import org.junit.jupiter.api.Test

object InitData {
    val userId = Id()

    @Test
    fun insertInitialData() {

        UserRepository.save(
            arrayListOf(
                User(
                    userId,
                    "undergraduate",
                    "张三",
                    UserRole.Undergraduate,
                    "undergraduate",
                    UndergraduatePrivilege(),
                    ArrayList(),
                    ArrayList(),
                    ArrayList()
                ),
                User(
                    Id(),
                    "graduate",
                    "李四",
                    UserRole.Graduate,
                    "graduate",
                    GraduatePrivilege(),
                    arrayListOf(),
                    ArrayList(),
                    ArrayList()
                ),
                User(
                    Id(),
                    "admin",
                    "admin",
                    UserRole.Admin,
                    "admin",
                    AdminPrivilege(),
                    ArrayList(),
                    ArrayList(),
                    ArrayList()
                )
            )
        )

        val book1Id = Id()
        val book2Id = Id()

        BookRepository.save(
            arrayListOf(
                Book(
                    book1Id,
                    "1",
                    "1",
                    arrayListOf(
                        Txt("这是书1的txt文本内容。"),
                        Pdf("https://www.gnu.org/software/gnu-c-manual/gnu-c-manual.pdf")
                    ),
                    arrayListOf(
                        BookInstance(Id(), book1Id),
                        BookInstance(Id(), book1Id)
                    )
                ),
                Book(
                    book2Id,
                    "2",
                    "2",
                    arrayListOf(
                        Txt("这是书2的txt文本内容。")
                    ),
                    arrayListOf(
                        BookInstance(Id(), book2Id),
                        BookInstance(Id(), book2Id)
                    )
                )
            )
        )


    }
}
