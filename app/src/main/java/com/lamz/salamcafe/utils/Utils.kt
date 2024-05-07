package com.lamz.salamcafe.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.lamz.salamcafe.ui.navigation.Screen

object Utils {

    val listScreenWithoutBottomBar = listOf(
        Screen.DetailCoffe.route
    )

    fun shareOrder(context: Context, summary: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
           data =  Uri.parse("https://wa.me/6287830314466?text=$summary")

        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp tidak terinstal", Toast.LENGTH_SHORT).show()
        }
    }

    fun toLinkedin(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data =  Uri.parse("https://www.linkedin.com/in/andi-salam-syahputra-534106291/")

        }
            context.startActivity(intent)
    }
    fun toGithub(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data =  Uri.parse("https://github.com/lamz16")

        }
            context.startActivity(intent)
    }

    fun toInstagram(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data =  Uri.parse("https://www.instagram.com/avada.kedaavra/")

        }
        context.startActivity(intent)
    }



}