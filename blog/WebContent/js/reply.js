//function replyWrite(boardId, userId){
//	console.log(userId)
//	if(userId === undefined) {
//		alert("로그인이 필요합니다.")
//		return;
//	}
//	
//	var data = {
//		boardid: boardId,
//		userid: userId,
//		content: $("#reply__write__form").val()
//	};
//	
//	$.ajax({
//		type: "post",
//		url: "/blog/reply?cmd=writeProc",
//		data: JSON.stringify(data),
//		contentType: "application/json; charset=utf-8",
//		dataType: "json"
//	}).done(function(result){
//		if(result == -1 || result == 0){
//			alert("댓글 작성 실패");
//		}else{
//			alert("댓글 작성 성공");
//			// 1. reply__list를 찾아서 내부를 비우기
//			$("#reply__list").empty();
//			console.log(result);
//			// 2. ajax 재호출 findAll()
//			renderReplyList(result);
//			// 3.reply__list를 찾아서 내부에 채워주기
//			$("#reply__write__form").val("");
//		}
//	}).fail(function(error){
//		alert("댓글 작성 실패");
//	});
//}

function replyDelete (replyId){
	console.log(replyId);
	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=deleteProc",
		data: "replyId="+replyId,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "text"
	}).done(function(result){
		if(result == 1){
			alert("댓글 삭제 성공");
			var replyItem = $("#reply-"+replyId);
			replyItem.remove();
		}else{
			alert("댓글 삭제 실패")
		}
		
	}).fail(function(error){
		alert("댓글 삭제 실패");
	});
}

function replyWrite(boardId, userId){
	console.log(userId)
	if(userId === undefined) {
		alert("로그인이 필요합니다.");
		return;
	}
	
	var data = {
		boardid: boardId,
		userid: userId,
		content: $("#reply__write__form").val()
	};
	
	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=writeProc",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(result){
		if(result == -1 || result == 0){
			alert("댓글 작성 실패");
		}else{
			alert("댓글 작성 성공");
			// 1. reply__list를 찾아서 내부를 비우기
			$("#reply__list").empty();
			console.log(result);
			// 2. ajax 재호출 findAll()
			renderReplyList(result);
			// 3.reply__list를 찾아서 내부에 채워주기
			$("#reply__write__form").val("");
		}
	}).fail(function(error){
		alert("댓글 작성 실패");
	});
}

function renderReplyList(replyDtos){
	for(var replyDto of replyDtos){
		$("#reply__list").append(makeReplyItem(replyDto));
	}
}

function makeReplyItem(replyDto){
	var replyItem = `<li id="reply-${replyDto.reply.id}" class="media">`;
	if(replyDto.userProfile == null){
		replyItem += `<img src="/blog/img/userProfile.png" class="img-circle">`;	
	}else{
		replyItem += `<img src="${replyDto.userProfile}" class="img-circle">`;
	}
	
	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
	replyItem += `<p>${replyDto.reply.content}</p>`;
	replyItem += `</div>`;
	//휴지통 추가 시작
	replyItem += `<div class="m-2">`;
	replyItem += `<i onclick="replyDelete(${replyDto.reply.id})" class="material-icons i__btn">delete</i>`;
	replyItem += `</div>`;
	//휴지통 추가 끝
	replyItem += `</li>`;
	return replyItem;
}