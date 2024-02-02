package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.tickets.userinfo.domain.entity.UserInfo

class IsContinueAvailableUseCase {

    operator fun invoke(userInfo: UserInfo): Boolean {
        return userInfo.firstName.error == null && userInfo.lastName.error == null
                && userInfo.middleName.error == null && userInfo.phone.error == null
    }

}