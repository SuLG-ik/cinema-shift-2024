package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.profile.edit.domain.entity.UserProfileInput

class IsContinueAvailableUseCase {

    operator fun invoke(userInfo: UserProfileInput?): Boolean {
        return  userInfo != null && userInfo.firstName.error == null && userInfo.lastName.error == null
                && userInfo.middleName.error == null && userInfo.city.error == null
                && userInfo.email.error == null
    }

}