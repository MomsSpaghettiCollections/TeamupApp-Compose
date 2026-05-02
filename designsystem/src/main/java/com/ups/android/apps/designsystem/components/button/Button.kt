package com.ups.android.apps.designsystem.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.ups.android.apps.designsystem.R
import com.ups.android.apps.designsystem.UiText
import com.ups.android.apps.designsystem.components.text.TextCompose
import com.ups.android.apps.designsystem.components.text.TextType
import com.ups.android.apps.designsystem.theme.LocalShapes
import kotlinx.coroutines.launch

@Composable
fun ButtonCompose(
    text: String,
    buttonType: ButtonType = ButtonType.FILLED_TONAL,
    isFullWidth: Boolean = false,
    icon: Int? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.buttonWidth(isFullWidth),
        colors = buttonType.getButtonColor(),
        elevation = buttonType.getButtonElevation(),
        shape = LocalShapes.current.button
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            content = {
                icon?.let {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "$text button",
                        modifier = Modifier.size(size = 24.dp)
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                }
                TextCompose(
                    text = text,
                    textType = buttonType.getTextType()
                )
            })
    }
}

@Composable
fun GoogleButton(
    text: String,
    onSuccess: (Credential) -> Unit,
    onFailure: (UiText) -> Unit
) {
    val context = LocalContext.current
    val manger = CredentialManager.create(context)
    val scope = rememberCoroutineScope()
    val webClientId = stringResource(R.string.web_client_id)

    Button(
        content = {
            TextCompose(
                text = text,
                textType = TextType.HEADLINE_MEDIUM
            )
        },
        onClick = {
            val option = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(webClientId)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(option)
                .build()
            scope.launch {
                try {
                    val result = manger.getCredential(context, request)
                    onSuccess(result.credential)
                } catch (ex: Exception) {
                    onFailure(UiText.SimpleString(ex.message.toString()))
                }
            }
        })
}