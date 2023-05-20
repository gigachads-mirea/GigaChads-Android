package com.rodyapal.gigachads.model.network.api

import com.rodyapal.gigachads.model.entity.Comment
import com.rodyapal.gigachads.model.entity.Game
import com.rodyapal.gigachads.model.entity.Post
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

class GameApi(
	private val client: HttpClient,
	private val baseURL: String
) : BaseApi<Game>(client, baseURL)

class PostApi(
	private val client: HttpClient,
	private val baseURL: String
) : BaseApi<Comment>(client, baseURL) {
	suspend fun getByServerId(serverId: Long): List<Post> {
		delay(4000)
		return listOf(
			Post(
				title = "First Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				likes = 10,
				serverId = serverId,
				id = 1
			),
			Post(
				title = "Second Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				likes = 5,
				serverId = serverId,
				id = 2
			),
			Post(
				title = "Third Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				likes = 3,
				serverId = serverId,
				id = 3
			),
			Post(
				title = "Fourth Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				likes = 8,
				serverId = serverId,
				id = 4
			),
			Post(
				title = "Fifth Post",
				content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed quam id est varius convallis. Fusce eget tellus ex. Sed non turpis eget turpis faucibus lacinia. Pellentesque non urna magna. Sed feugiat orci ut lectus efficitur, ut ullamcorper elit interdum. Morbi auctor massa vel ipsum commodo facilisis. Curabitur quis ipsum sollicitudin, vulputate neque sed, lobortis velit. Nunc consectetur tincidunt mi, vel facilisis ex luctus ut. In venenatis iaculis nibh, in pharetra lorem euismod sed. Fusce vitae ligula non libero pellentesque aliquet. Mauris placerat auctor augue, non finibus tellus cursus eu. Nullam nec turpis gravida, lacinia elit non, volutpat dui. Quisque at lacinia purus. Suspendisse potenti.\n" +
						"\n" +
						"This paragraph of Lorem Ipsum text is often used as a placeholder in design and typesetting to demonstrate the visual effects of different fonts, layouts, and spacing. It doesn't have any actual meaning, as it is derived from a Latin text that has been scrambled and altered.",
				writtenAt = System.currentTimeMillis(),
				likes = 12,
				serverId = serverId,
				id = 5
			)
		)
	}
}

class ServerApi(
	private val client: HttpClient,
	private val baseURL: String
) : BaseApi<Comment>(client, baseURL)