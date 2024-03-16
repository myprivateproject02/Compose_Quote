package com.jadhavrupesh.composequote.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jadhavrupesh.composequote.R
import com.jadhavrupesh.composequote.model.Quote

@Composable
fun RandomQuoteScreen(quote: Quote) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        quote.tags.forEach { tag ->
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = tag,
                        fontFamily = FontFamily.Serif
                    )
                })

        }
        Image(
            painter = painterResource(id = R.drawable.quote),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
        )
        Text(
            text = quote.content,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.size(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(.96f), contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "- ${quote.author}",
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp
            )
        }

    }

}