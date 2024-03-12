package com.example.bonusmoney.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.example.bonusmoney.R
import com.example.bonusmoney.api.Company
import com.example.bonusmoney.api.CompanyId
import com.example.bonusmoney.api.CustomerMarkParameters
import com.example.bonusmoney.api.LoyaltyLevel
import com.example.bonusmoney.api.MobileAppDashboard
import com.example.bonusmoney.utils.Config

@Composable
fun CompanyItem(
    company: Company,
    context: Context,
    modifier: Modifier = Modifier
) {
    val design = company.mobileAppDashboard
    val loyalty = company.customerMarkParameters.loyaltyLevel
    val mark = company.customerMarkParameters.mark
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(design.cardBackgroundColor.color())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = company.mobileAppDashboard.companyName,
                    fontSize = 20.sp,
                    fontFamily = Config.segoe,
                    color = design.highlightTextColor.color(),
                    textAlign = TextAlign.Start
                )

                AsyncImage(
                    model = design.logo,
                    contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    alignment = Alignment.CenterEnd
                )
            }

            SmallSpace()

            Line(design.textColor.color())

            LargeSpace()

            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = design.highlightTextColor.color(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(mark.toInt().toString())
                        }
                        append(" ")
                        append(mark.toInt().case("балл"))
                    },
                    fontSize = 16.sp,
                    fontFamily = Config.segoe,
                    color = design.textColor.color()
                )
            }

            LargeSpace()

            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Кэшбек",
                        fontSize = 12.sp,
                        fontFamily = Config.segoe,
                        color = design.textColor.color()
                    )

                    Text(
                        text = "${loyalty.cashToMark.toInt()}%",
                        fontSize = 14.sp,
                        fontFamily = Config.segoe,
                        color = design.highlightTextColor.color()
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Уровень",
                        fontSize = 12.sp,
                        fontFamily = Config.segoe,
                        color = design.textColor.color()
                    )

                    Text(
                        text = loyalty.name,
                        fontSize = 14.sp,
                        fontFamily = Config.segoe,
                        color = design.highlightTextColor.color()
                    )
                }
            }

            SmallSpace()

            Line(design.textColor.color())

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(48.dp),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Eye icon",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                pressed(context, company, "глазик")
                            },
                        colorFilter = ColorFilter.tint(design.mainColor.color())
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Trash icon",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                pressed(context, company, "мусорка")
                            },
                        colorFilter = ColorFilter.tint(design.accentColor.color())
                    )
                }

                Spacer(Modifier.size(48.dp))

                Button(
                    onClick = {
                        pressed(context, company, "подробнее")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = design.backgroundColor.color()
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                        .height(40.dp)
                        .width(150.dp)
                ) {
                    Text(
                        text = "Подробнее",
                        fontSize = 14.sp,
                        fontFamily = Config.segoe,
                        color = design.mainColor.color()
                    )
                }
            }
        }
    }
}

@Composable
fun LargeSpace() {
    Spacer(Modifier.size(16.dp))
}

@Composable
fun SmallSpace() {
    Spacer(Modifier.size(8.dp))
}

@Composable
fun Line(color: Color) {
    HorizontalDivider(
        color = color,
        modifier = Modifier.padding(
            horizontal = 16.dp
        ),
        thickness = 0.dp
    )
}

@Preview
@Composable
fun ItemPreview() {
    val context = LocalContext.current
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            CompanyItem(
                company = Company(
                    company = CompanyId("smth"),
                    customerMarkParameters = CustomerMarkParameters(
                        loyaltyLevel = LoyaltyLevel(
                            2.0,
                            "MonsterLevel",
                            3000.0,
                            1234.0,
                            2234.0
                        ),
                        mark = 23456.0
                    ),
                    mobileAppDashboard = MobileAppDashboard(
                        companyName = "Monster",
                        logo = "https://calorizator.ru/sites/default/files/imagecache/recipes_full/recipe/32319.jpg",
                        backgroundColor = "#FFFFFF",
                        mainColor = "#2688EB",
                        cardBackgroundColor = "#EFEFEF",
                        textColor = "#1A1A1A",
                        highlightTextColor = "#3700B3",
                        accentColor = "#03DAC5"
                    )
                ),
                context
            )

        }
    }
}

private fun pressed(context: Context, company: Company, buttonName: String) {
    toast(
        context,
        "нажата кнопка $buttonName, id компании: ${company.company.companyId}"
    )
}

private fun String.color(): Color = Color(this.toColorInt())

private fun Int.case(root: String): String {
    return when (this % 10) {
        1 -> root
        2, 3, 4 -> root + "а"
        else -> root + "ов"
    }
}
