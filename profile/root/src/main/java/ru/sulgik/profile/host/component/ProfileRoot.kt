package ru.sulgik.profile.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sulgik.profile.edit.component.ProfileEdit

interface ProfileRoot {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Edit(
            val component: ProfileEdit,
        ) : Child

        data class Login(val component: ru.sulgik.login.component.Login) : Child

        data object Loading : Child

    }
}