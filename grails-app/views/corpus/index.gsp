<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="left-container">
				<div id="left-container-top">
					<div>
						<h4>Corpus</h4>
					</div>
					<div>
						<select>
							<% corpus.each{ corpora -> %>
						
								<option><%="${corpora.name}"%></option>
					
							<% } %>
						</select>
					</div>
				</div>
				<div id="left-container-bottom">
					<div>
						<h4>Directory</h4>
						<ul class="nav nav-tabs nav-stacked">
							<% corpusDirs.each{ cdir -> %>
						
								<li><a href="./<%="${cdir.id}"%>"><%="${cdir.name}"%></a></li>
					
							<% } %>
						</ul>
					</div>
				</div>
			</div>
			<div class="span9" id="right-container">
				<ul class="nav nav-tabs">
					<li><a href="#home" data-toggle="tab">Corpus</a></li>
					<li><a href="#home" data-toggle="tab">Settings</a></li>
				</ul>
				<div>
					<table class="table">
					<thead>
						<tr>
							<th>File Name</th>
							<th>Mimetype</th>
						</tr>
					</thead>
					<tbody>
						<% audioFiles.each{ audioFile -> %>
						<tr>
								<td><%="${audioFile.name}"%></td>
	 							<td><%="${audioFile.mimetype}"%></td> 
						</tr>
						<%  } %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>