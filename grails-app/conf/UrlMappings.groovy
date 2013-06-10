class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?/$id2?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
