package com.example.elaboratomobile.ui.screens.books

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.elaboratomobile.data.database.Genere
import com.example.elaboratomobile.ui.BookShareRoute
import com.example.elaboratomobile.ui.composables.RatingBarNoClick

@Composable
fun HomeBooksScreen(
    navController: NavHostController,
    list: List<BookLike>,
    listGeneri: List<Genere>,
    currentIdGenere: Int,
    like: (Int) -> Unit,
    comboAction: (Int) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        ComboBox(comboAction, listGeneri, currentIdGenere)

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 80.dp),
            modifier = Modifier.padding()
        ) {
            items(list) { bookLike ->
                BookItem(
                    book = bookLike,
                    onClick = {
                        navController.navigate(BookShareRoute.BookDetails.buildRoute(bookLike.id_libro))
                    }, onLikeClicked = like
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(
    book: BookLike,
    onClick: () -> Unit,
    onLikeClicked: (Int) -> Unit
) {
    val colorCard = MaterialTheme.colorScheme.onPrimary
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
            containerColor = colorCard
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (book.copertina != null) {
                        book.copertina.let { nonNullBitmap ->
                            val imageBitmap: ImageBitmap = nonNullBitmap.asImageBitmap()
                            Image(
                                bitmap = imageBitmap,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(130.dp)
                                    .padding(end = 10.dp)
                                    .fillMaxWidth(0.4f)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.AccountBox,
                            contentDescription = "Icona del profilo",
                            modifier = Modifier.size(130.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = book.titolo, color = MaterialTheme.colorScheme.onSurface)
                        Text(text = book.autore, color = MaterialTheme.colorScheme.onSurface)
                        Text(text = book.genereNome, color = MaterialTheme.colorScheme.onSurface)
                        RatingBarNoClick(rating = book.recensione)
                    }
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    onClick = { onLikeClicked(book.id_libro) },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Preferito",
                        tint = if (book.isLiked) MaterialTheme.colorScheme.tertiary else Color.Gray
                    )
                }
            }
        }
    }
}


@Composable
fun ComboBox(comboAction: (Int) -> Unit, listGeneri: List<Genere>, currentIdGenere: Int) {
    var expanded by remember { mutableStateOf(false) }
    var selectedGenreId by remember { mutableStateOf<Int?>(currentIdGenere) }

    val options = listOf(Genere(0, "Tutti i generi")) + listGeneri

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(8.dp)
                .clickable { expanded = !expanded }
                .border(color = Color.Black, width = 1.dp, shape = RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(8.dp))
                .padding(10.dp)
        ) {
            Text(

                text = options.first { it.id_genere == selectedGenreId }.nome,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown",
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(focusable = false)
        ) {
            options.forEach { genere ->
                DropdownMenuItem(
                    text = { Text(genere.nome) },
                    onClick = {
                        selectedGenreId = genere.id_genere
                        comboAction(genere.id_genere)
                        expanded = false
                    }
                )
            }
        }
    }
}



