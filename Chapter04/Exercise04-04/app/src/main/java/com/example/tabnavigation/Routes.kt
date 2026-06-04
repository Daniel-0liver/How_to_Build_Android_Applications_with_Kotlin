package com.example.tabnavigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination(val label: String) {
    @Serializable
    data object TopStories : Destination("Top Stories")
    @Serializable
    data object UKNews : Destination("UK News")
    @Serializable
    data object Politics : Destination("Politics")
    @Serializable
    data object Business : Destination("Business")
    @Serializable
    data object WorldNews : Destination("World News")
    @Serializable
    data object Sport : Destination("Sport")
    @Serializable
    data object Other : Destination("Other")
}

sealed class TabNavigation(
    val label: String,
    val route: Destination
) {
    data object TopStories : TabNavigation(
        label = "Top Stories",
        route = Destination.TopStories
    )
    data object UKNews : TabNavigation(
        label = "UK News",
        route = Destination.UKNews
    )
    data object Politics : TabNavigation(
        label = "Politics",
        route = Destination.Politics
    )
    data object Business : TabNavigation(
        label = "Business",
        route = Destination.Business
    )
    data object WorldNews : TabNavigation(
        label = "World News",
        route = Destination.WorldNews
    )
    data object Sport : TabNavigation(
        label = "Sport",
        route = Destination.Sport
    )
    data object Other : TabNavigation(
        label = "Other",
        route = Destination.Other
    )
}