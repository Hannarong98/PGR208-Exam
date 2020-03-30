package no.kristiania.foreignlands

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import no.kristiania.foreignlands.data.repository.DetailsRepository


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
