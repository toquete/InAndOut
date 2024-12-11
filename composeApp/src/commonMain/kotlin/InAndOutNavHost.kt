import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.toquete.inandout.addTransactionScreen
import com.toquete.inandout.homeScreen

@Composable
fun InAndOutNavHost(navController: NavHostController,) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        homeScreen { navController.navigate("addTransaction") }
        addTransactionScreen { navController.navigateUp() }
    }
}