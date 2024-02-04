package ru.sulgik.profile.edit.data.converter

import ru.sulgik.profile.edit.data.model.UserDetailsResponse
import ru.sulgik.profile.edit.domain.entity.UserProfile

class KtorUserConverter {

    fun convert(
        userDetailsResponse: UserDetailsResponse
    ): UserProfile {
        return userDetailsResponse.user.convert()
    }

    private fun UserDetailsResponse.User.convert(): UserProfile {
        return UserProfile(
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            middleName = middleName.orEmpty(),
            email = email.orEmpty(),
            city = city.orEmpty(),
            phone = phone.substring(1)

        )
    }

}