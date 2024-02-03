package ru.sulgik.profile.edit.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.ValueContainer
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.profile.edit.domain.entity.UserProfileInput
import ru.sulgik.profile.edit.presentation.ProfileEditStore

class ProfileEditComponent(
    componentContext: DIComponentContext,
    private val onUnauthorized: () -> Unit,
) : AppComponentContext(componentContext), ProfileEdit {

    private val coroutineScope = coroutineScope()
    private val store: ProfileEditStore = getStore()

    init {
        store.labels.onEach {
            when (it) {
                ProfileEditStore.Label.Unauthorized -> onUnauthorized()
            }
        }.launchIn(coroutineScope)
    }

    private val state = store.values(this)

    override val userProfileInput: Value<ValueContainer<UserProfileInput?>> =
        state.map { ValueContainer(it.profile) }

    override val isContinueAvailable: Value<Boolean> =
        state.map { it.isContinueAvailable }

    override val isLoading: Value<Boolean> =
        state.map { it.isLoading }

    override fun onContinue() {
        store.accept(ProfileEditStore.Intent.Save)
    }

    override fun onFirstNameInput(value: String) {
        store.accept(ProfileEditStore.Intent.FirstNameInput(value))
    }

    override fun onLastNameInput(value: String) {
        store.accept(ProfileEditStore.Intent.LastNameInput(value))
    }

    override fun onMiddleNameInput(value: String) {
        store.accept(ProfileEditStore.Intent.MiddleNameInput(value))
    }

    override fun onEmailNameInput(value: String) {
        store.accept(ProfileEditStore.Intent.EmailInput(value))
    }

    override fun onCityNameInput(value: String) {
        store.accept(ProfileEditStore.Intent.CityInput(value))
    }

}