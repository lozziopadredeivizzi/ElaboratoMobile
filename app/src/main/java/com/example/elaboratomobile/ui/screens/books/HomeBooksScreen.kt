package com.example.elaboratomobile.ui.screens.books

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elaboratomobile.R
import com.example.elaboratomobile.ui.BookShareRoute
import com.example.elaboratomobile.ui.composables.RatingBarNoClick

@Composable
fun HomeBooksScreen(navController: NavHostController) {
    val lista = (1..20).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 80.dp),
        modifier = Modifier.padding()
    ) {
        items(lista) {
            BookItem(item = it, onClick = {
                navController.navigate(BookShareRoute.BookDetails.route)
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(item: Int, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .width(70.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Blue)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) { // Box che riempie l'intera area disponibile
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.copertina),
                    contentDescription = "Cover",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(130.dp)
                        .padding(end = 30.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Titolo")
                    Text(text = "Autore")
                    Text(text = "Genere")
                    RatingBarNoClick(rating = 3.5)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            // Posizionamento dell'IconButton
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(25.dp)

            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Preferito",
                    tint = Color.Red
                )
            }
        }

    }

}