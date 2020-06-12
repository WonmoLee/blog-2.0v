$("#img__preview").on("change", function(e){
		var f = e.target.files[0];
		if(!f.type.match("image*")){
			alert("이미지만 첨부할 수 있습니다.");
			return;
		}
		
		//f.size = 1024*1024*2
		if(f.size >= 1024*1024*2) {
			return;
		}
		var reader = new FileReader();

		reader.onload = function(e){
			$("#img__wrap").attr("src", e.target.result);
		};
		
		// 바구니 채워 놓는 시간이 2초
		reader.readAsDataURL(f); // 파일 읽기 (비동기 실행)
		
});
