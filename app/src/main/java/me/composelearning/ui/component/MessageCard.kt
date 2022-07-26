package me.composelearning.ui.component

import me.composelearning.R
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.composelearning.model.Message
import me.composelearning.ui.theme.ComposeLearningTheme
import me.composelearning.ui.theme.Gray700
import org.junit.Test


@Composable
fun MessageCard (msg: Message){
    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember { mutableStateOf(false) }
    // surfaceColor will be updated gradually from one color to the other
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
    )

    Row(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
               .clickable { isExpanded = !isExpanded }
    ) {
        Image(painter = painterResource(id = R.drawable.ic_profile_picker),
            contentDescription = "contact profile picture",
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape)
                .border(1.4.dp, MaterialTheme.colors.secondary, CircleShape) )
        Spacer(modifier = Modifier.width(8.dp))


        // We toggle the isExpanded variable when we click on this Column
        Column( verticalArrangement = Arrangement.Center ) {
            Text(text = msg.author,fontSize = 16.sp,
                color = MaterialTheme.colors.secondary
                // color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                elevation = 2.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text( modifier = Modifier.padding(all= 4.dp),
                    text = msg.body,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    color = if (isExpanded) Color.White else Gray700,
                    fontSize = 14.sp)
            }
        }
    }
}


@Preview( name = "Light Mode")
@Preview( name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true)
@Composable
fun MessageCardPreview() {
    ComposeLearningTheme {
        MessageCard(Message( "Jean","JetPack Compose"))
    }
}