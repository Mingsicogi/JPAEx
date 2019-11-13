package com.example.demo;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@Slf4j
@DataJpaTest
public class AsyncRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@Test
	public void a_비동기_쿼리_테스트() throws ExecutionException, InterruptedException {

		Post post = new Post();
		post.setTitle("JPA 비동기 쿼리 테스트입니다.");

		postRepository.save(post);
		postRepository.flush(); // 강제로 쿼리 수행

		Future<List<Post>> dbInfo = postRepository.findAllByTitleContains("JPA");

		Thread.sleep(1000);
		if(dbInfo.isDone()){
			System.out.println("===========Future is done============");
		}

		// future로 전달 받은 select 결과가 존재하지 않음. 비동기로 진행되면서 다른 thread가 다른 db connect으로 select 했기 때문에 저장된 값을 보지 못함. (Transaction 상태이기 때문에)
		// TC를 작성하기 어려움.
		List<Post> posts = dbInfo.get();
		System.out.println("================Select result===================");
		posts.forEach(System.out::println);
	}
}
