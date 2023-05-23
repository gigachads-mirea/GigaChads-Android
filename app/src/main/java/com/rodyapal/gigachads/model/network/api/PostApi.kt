package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.network.entity.NetworkPost
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

const val POST_API_URL = ""

class PostApi(
	private val client: HttpClient,
) {
	suspend fun get(postId: Long): NetworkPost {
		delay(1000)
		return NetworkPost(
			title = "First Post",
			content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
					"\n" +
					"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
			writtenAt = System.currentTimeMillis(),
			serverId = 0,
			id = 1
		)
	}

	suspend fun getByServerId(serverId: Long): List<NetworkPost> {
		delay(4000)
		return listOf(
			NetworkPost(
				title = "First Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				serverId = serverId,
				id = 1
			),
			NetworkPost(
				title = "Second Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				serverId = serverId,
				id = 2
			),
			NetworkPost(
				title = "Third Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				serverId = serverId,
				id = 3
			),
			NetworkPost(
				title = "Fourth Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				serverId = serverId,
				id = 4
			),
			NetworkPost(
				title = "Fifth Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				serverId = serverId,
				id = 5
			)
		)
	}
}
