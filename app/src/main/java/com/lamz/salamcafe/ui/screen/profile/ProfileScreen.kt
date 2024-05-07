package com.lamz.salamcafe.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lamz.salamcafe.R
import com.lamz.salamcafe.ui.theme.FEJetpackComposeTaskTheme
import com.lamz.salamcafe.ui.theme.myColor4
import com.lamz.salamcafe.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,

) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Profile",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.Black
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(myColor4)
        )

        Box(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Image Andi",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.icon_android_dev),
                contentDescription = "android-dev",
                modifier = Modifier
                    .padding(end = 18.dp, bottom = 8.dp)
                    .size(36.dp)
                    .align(Alignment.BottomEnd)

            )
        }

        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Row {
                    Text(text = "Nama", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(31.dp))
                    Text(text = ":", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Andi Salam Syahputra",
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }

                Row {
                    Text(text = "Email", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(32.5.dp))
                    Text(text = ":", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "andiku0755@gmail.com",
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }

                Row {
                    Text(text = "Asal Univ", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = ":", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Universitas Amikom Yogyakarta",
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }

                Row {
                    Text(text = "Jurusan", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = ":", style = TextStyle(fontWeight = FontWeight.SemiBold))
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Sistem Informasi",
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

        }

        Row(Modifier.padding(top = 24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.linkedin_logo),
                contentDescription = "Linkedin",
                modifier = Modifier
                    .height(24.dp)
                    .width(80.dp)
                    .clickable {
                      Utils.toLinkedin(context)
                    }
            )
            Spacer(modifier = Modifier.width(24.dp))
            Image(
                painter = painterResource(id = R.drawable.github_logo),
                contentDescription = "Github",
                modifier = Modifier
                    .height(28.dp)
                    .width(90.dp)
                    .clickable {
                        Utils.toGithub(context)
                    }
            )
            Spacer(modifier = Modifier.width(24.dp))
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "Github",
                modifier = Modifier
                    .height(28.dp)
                    .width(120.dp)
                    .clickable {
                        Utils.toInstagram(context)
                    }
            )
        }




    }
}


@Composable
@Preview(showBackground = true)
private fun preview() {
    FEJetpackComposeTaskTheme {
        ProfileScreen()
    }
}