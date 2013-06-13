<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'inlineplayer.css')}"
	type="text/css">

<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'flashblock.css')}" type="text/css">
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

					</div>
				</div>
			</div>
			<div class="span9" id="right-container">
				<div class="row-fluid">
					<div class="span6">
						<h3>
							${corpus.name}
						</h3>
					</div>
					<div class="span6">
						<g:paginate next="&rsaquo;" prev="&laquo;" controller="corpus"
							action="index" params="[id:params.id]" total="${fileCount}" />

					</div>
				</div>
				<div>
					<g:render template="audiofile" collection="${audioFiles}" />
				</div>
			</div>
		</div>
	</div>



	<g:javascript library="soundmanager2" />
	<g:javascript library="inline" />

	<script type="text/javascript">
		$(document).ready(function() {
			$('div.pagination').addClass('pagination-mini').addClass('pagination-right').css("margin","0px").css("margin-top","30px");
			$('hr').css("margin","5px");
		});
	</script>
</body>
</html>