<template>
  <div>
    <div class="jumbotron text-center">
      <h1>CRUD Blog Tutorial</h1>
      <p>
        This is the description of the blog built with Vue.Js, Spring Boot,
        Mongo DB
      </p>
     
    </div>
    <div class="container">
      <div v-if="posts.length === 0" class="text-center">
        <h2>No post found at the moment</h2>
      </div>
      <div class="row">
        <div class="col-md-4" v-for="post in posts" :key="post._id">
          <div class="card mb-4 shadow-sm">
            <div class="card-body">
              <h2 class="card-img-top">{{ post.title }}</h2>
              <p class="card-text">{{ post.description }}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group" style="margin-bottom: 20px">
                  <router-link
                    :to="{ name: 'post', params: { id: post.id } }"
                    class="btn btn-sm btn-outline-secondary"
                    >View Post
                  </router-link>
                  <router-link
                    :to="{ name: 'edit', params: { id: post.id } }"
                    class="btn btn-sm btn-outline-secondary"
                    >Edit Post
                  </router-link>
                  <button
                    class="btn btn-sm btn-outline-secondary"
                    v-on:click="deletePost(post.id)"
                  >
                    Delete Post
                  </button>
                </div>
              </div>
              <div class="card-footer">
                <small class="text-muted"
                  >Posted on: {{ post.date_posted }}</small
                ><br />
                <small class="text-muted">by: {{ post.author }}</small>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { server } from "@/utils/helper";
import axios from "axios";

export default {
  data() {
    return {
      posts: [],
    };
  },
  created() {
    this.fetchPosts();
  },
  methods: {
    fetchPosts() {
      axios
        .get(`${server.baseURL}/blog/contents`)
        .then((data) => (this.posts = data.data));
    },
    deletePost(id) {
      axios
        .delete(`${server.baseURL}/blog/content/${id}`)
        .then((data) => {
          window.location.reload();
        });
    },
  },
};
</script>
