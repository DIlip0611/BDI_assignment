<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Your Todos</h1>
	<table class="table">
		<thead>
		<tr>
			<th>ID</th>
			<th>Description</th>
			<th></th>
			<th></th>
		</tr>
		</thead>
			<tbody>
				<c:forEach items="${todos}" var = "todo">
					<tr>
					
						<td>${todo.id}</td>
						<td>${todo.description}</td>
						<td><a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
						<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
					</tr>
			</c:forEach>
				</tbody>
			
		</table>
		<a href="add-todo" class="btn btn-success">Add Todo</a>
	</div>
	
	<%@ include file="common/footer.jspf" %>