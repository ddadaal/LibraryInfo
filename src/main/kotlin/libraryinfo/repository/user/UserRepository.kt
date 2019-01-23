package libraryinfo.repository.user

import libraryinfo.entity.user.User
import libraryinfo.repository.Repository

object UserRepository: Repository<ArrayList<User>>("users", ArrayList())