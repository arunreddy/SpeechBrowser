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
			<div class="span8" id="right-container">
				<div class="row-fluid">
					<% if(corpus!=null){ %>
						<div class="span6">
							<h3>${corpus.name}</h3>
						</div>
						<div class="span6">
							<g:paginate next="&rsaquo;" prev="&laquo;" controller="corpus"
								action="index" params="[id:params.id]" total="${fileCount}" />
						</div>
					<% }else{ %>
						<div class="alert">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Warning!</strong> Database is empty. Synchronize datasets.
					</div>
					<% }%>
				</div>
				<div>
					<g:render template="audiofile" collection="${audioFiles}" />
				</div>
			</div>
			<!-- end right-container -->
			<div class="span4" id="left-container">
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
						<h4>Segments</h4>
						<div id="segments"></div>
					</div>
				</div>
			</div>
			<!-- end left-container -->
		</div>
		<!-- end row-fluid -->
	</div>



	<g:javascript library="soundmanager2" />
	<g:javascript library="inline" />

	<script type="text/javascript">
		$(document).ready(
				function() {
					$('div.pagination').addClass('pagination-mini').addClass(
							'pagination-right').css("margin", "0px").css(
							"margin-top", "30px");
					$('hr').css("margin", "5px");

					//When utterance button is clicked show the utterance.

					//When segments is clicked...
					$('button.segment').each(function() {
						$(this).click(function() {

							$('#segments').html('<div class="loader"><span></span><span></span><span></span></div>')
							//Ajax Post.
							var idVar = $(this).attr('audio')
							$.get("../fetchsegments", {
								id : idVar
							}, function(data) {
								$('#segments').html(data)
							});

						}); //missing ); here!
					});
				});
	</script>
</body>
</html>