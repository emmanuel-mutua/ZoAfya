package com.example.zoafya.ui.user.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zoafya.data.remote.services.Into
import com.example.zoafya.data.remote.services.IntroProviderObject

@Composable
fun CustomerHomeContent(
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 55.dp)
            .navigationBarsPadding()
            .padding(top = paddingValues.calculateTopPadding()),
    ) {
        item {
           LazyRow {
               items(IntroProviderObject.intro){
                   IntroCard(service = it)
               }
           }
        }

    }
}

@Composable
fun IntroCard(service: Into) {
    Card {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = service.image),
                contentDescription = service.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = service.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = service.description,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
