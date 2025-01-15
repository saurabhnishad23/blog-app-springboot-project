# blog-app-springboot-project

<H1>Blog Application - Spring Boot Project</H1>

<h2>Description </h2>

The Blog Application is a Spring Boot-based web application designed to manage blog posts. It allows users to create, view, edit, and delete blog posts. The application also includes features like user authentication, authorization, and a comment system for enhanced interaction.

<h2>Features</h2>
<li>User authentication (registration and login)</li>
<li>Role-based authorization (admin and user roles)</li>
<li>CRUD operations for blog posts</li>
<li>Add, view, and manage comments on posts</li>
<li>Search functionality to find blog posts</li>
<li>Pagination for efficient navigation through blog posts</li>
<li>RESTful APIs for various operations</li>

<h2>Technologies Used</h2>

<li>Spring Boot, Spring Security, Spring Data JPA</li>
<li>Database: MySQL (production)</li>
<li>Build Tool: Maven</li>

<h2>Requirements</h2>

<li>Java 8 or higher</li>
<li>Maven 3.6+</li>
<li>MySQL</li>

<h2>API Endpoints</h2>

The application will run on http://localhost:8080 <br>
The application provides the following API endpoints:

<h3>Public Endpoints</h3>

<li>POST /api/auth/register - Register a new user</li>
<li>POST /api/auth/login - Login and generate a JWT token</li>
<li>Protected Endpoints (requires authentication)</li>
<li>GET /api/posts - Get all posts (with pagination and search)</li>
<li>POST /api/posts - Create a new post (admin only)</li>
<li>PUT /api/posts/{id} - Update a post (admin only)</li>
<li>DELETE /api/posts/{id} - Delete a post (admin only)</li>
<li>POST /api/posts/{id}/comments - Add a comment to a post</li>
<li>GET /api/posts/{id}/comments - Get comments for a post</li>



