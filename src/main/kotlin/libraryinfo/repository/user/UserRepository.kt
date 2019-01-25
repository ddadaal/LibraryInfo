package libraryinfo.repository.user

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.Repository

object UserRepository: Repository<ArrayList<User>>("users", ArrayList())