package com.corbellini.jokes.features.jokes.ui.jokes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.corbellini.jokes.R
import com.corbellini.jokes.features.jokes.ui.theme.Teal200
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun JokeScreen(
    viewModel: JokeViewModel
) {
    val jokes = viewModel.jokes.collectAsState()
    val loading = viewModel.loading.collectAsState()
    val error = viewModel.error.collectAsState()
    val actionRandomJoke = viewModel::dispatchRandomJoke

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                backgroundColor = Teal200,
                text = { Text(text = "Random", color = Color.White) },
                onClick = actionRandomJoke
            )
        }
    ) {
        val modifier = Modifier.padding(it)

        Body(modifier, jokes.value, loading.value, error.value)
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    jokes: List<JokeView>,
    loading: Boolean,
    error: Int?
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        Text(
            stringResource(R.string.app_name),
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h1,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if(error != null){
            Box(
                modifier = Modifier.fillMaxWidth().padding(4.dp)
                    .background(
                        color = Color.Red
                    )
            ) {
                Text(
                    stringResource(id = error),
                    color = Color.White,
                    modifier = modifier.fillMaxWidth().padding(4.dp),)}
        }
        if(loading){
            LinearProgressIndicator(
                modifier = modifier.fillMaxWidth(),
                color = Color.White
                ,backgroundColor = Color.Cyan
            )
        }
        jokes.forEach {
            JokeContent(
                joke = it,
            )
        }
    }
}

@Composable
fun JokeContent(
    modifier: Modifier = Modifier,
    joke: JokeView,
) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout {
            val (image, name, dim) = createRefs()
            GlideImage(
                data = joke.iconUrl,
                contentDescription = null,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )


            Box(
                modifier = Modifier
                    .constrainAs(dim) {
                        start.linkTo(image.start)
                        top.linkTo(image.top)
                        end.linkTo(image.end)
                        bottom.linkTo(image.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .background(
                        Brush.verticalGradient(
                            0.1f to Color.Transparent,
                            1f to Color(0x4d000000),
                        )
                    )
            ) { }

            Text(
                text = joke.value,
                color = Color.White,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(image.bottom, margin = 8.dp)
                    }

            )
        }
    }
}