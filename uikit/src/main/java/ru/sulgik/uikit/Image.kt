package ru.sulgik.uikit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.compose.AsyncImagePainter
import coil3.compose.DefaultModelEqualityDelegate
import coil3.compose.EqualityDelegate
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageScope

val LocalImageLoader =
    staticCompositionLocalOf<ImageLoader?> { null }

@Composable
fun UIKitRemoteImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    transform: (AsyncImagePainter.State) -> AsyncImagePainter.State = AsyncImagePainter.DefaultTransform,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    clipToBounds: Boolean = true,
    modelEqualityDelegate: EqualityDelegate = DefaultModelEqualityDelegate,
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = contentDescription,
        imageLoader = LocalImageLoader.current ?: SingletonImageLoader.get(LocalContext.current),
        modifier = modifier,
        transform = transform,
        loading = loading,
        success = success,
        error = error,
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = onError,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        clipToBounds = clipToBounds,
        modelEqualityDelegate = modelEqualityDelegate
    )
}