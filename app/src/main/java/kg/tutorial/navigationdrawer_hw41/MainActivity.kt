package kg.tutorial.navigationdrawer_hw41

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        val toolbar = findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar?.title = "DrawerLayout + Fragment"

        replaceFragment(FirstFragment())

        val toggle:ActionBarDrawerToggle = object:ActionBarDrawerToggle(
                this,drawerLayout,toolbar,
                R.string.drawer_open,R.string.drawer_close){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }


        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)


        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when(it.itemId){
                R.id.first->{
                    replaceFragment(FirstFragment())
                    true
                }
                R.id.second->{
                    replaceFragment(SecondFragment())
                    true
                }
                R.id.third->{
                    replaceFragment(ThirdFragment())
                    true
                }
                else->false
            }
        }
    }
}

fun AppCompatActivity.replaceFragment(fragment:Fragment){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.host,fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}