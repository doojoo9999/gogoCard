api 정리 : https://docs.google.com/spreadsheets/d/1IWMzJh89v_LpKw273MpVAHRYexMn_mpybptselW3sUI/edit#gid=753593791


gogoCard API			
			
구분	API명	설명	
정보 생성	카드 생성	카드를 생성합니다.	
정보 조회	카드 조회	카드를 조회합니다.	
정보 조회	카드 목록 조회	카드 목록을 조회합니다.	
정보 수정	카드 수정	카드를 수정합니다.	
정보 삭제	카드 삭제	카드를 삭제합니다.	
			
			
			
카드 생성			
매서드	요청URL		
POST	http://{server_url}/api/gogocards		
			
Request			
파라미터	타입	필수여부	설명
title	String	필수	제목
content	String	필수	내용
date	String	필수	작성일
author	String	필수	작성자
			
Response			
파라미터	타입	필수여부	설명
id	Long	필수	고유 id
			
			
			
카드 조회			
매서드	요청URL		
GET	http://{server_url}/api/gogocards		
			
Request			
파라미터	타입	필수여부	설명
id	Long	필수	고유 id
			
Response			
파라미터	타입	필수여부	설명
title	String	필수	제목
content	String	필수	내용
date	String	필수	작성일
author	String	필수	작성자
			
			
			
카드 목록 조회			
매서드	요청URL		
GET	http://{server_url}/api/gogocards		
			
Request			
파라미터	타입	필수여부	설명
author	String	필수	작성자
			
Response			
파라미터	타입	필수여부	설명
title	String	필수	제목
id	Long	필수	고유 id


#URI

Index	Method	URI	Description
1	POST	/gogocards	할 일 카드 작성
2	GET	/gogocards/{cardId}	할 일 카드 조회
3	GET	/gogocards	할 일 카드 전체 목록 조회
4	DELETE	/gogocards/{cardId}	할 일 카드 삭제
5	PUT	/gogocards/{cardId}	할 일 카드 수정

/gogocards/{cardId}	할 일 카드 삭제						
							
	Method	DELETE					
							
	Header						
		Name	Type	Mandatory	Example	Detail	Description
							
	PARAM						
		Name	Type	Mandatory	Example	Detail	Description
		cardId	String	Y	1		1번 카드 선택
							
	BODY						
		Name	Type	Mandatory	Example	Detail	Description
	DELETE	id	Long	Y	1		삭제할 카드 ID
							
							
							
	Response Parameters						
		Name	Type	Mandatory	Example	Detail	Description
		success	Boolean	Y	Success		성공 여부
		message	String	Y	삭제 완료		사용자에게 알림
		error	String	Y	200		에러 내용 반환
							
							
							
	Result Code						
		Code	message				
		200	카드를 삭제했습니다.				
		400	유효하지 않은 데이터입니다.				

  
/gogocards/{cardId}	할 일 카드 수정						
							
	Method	PUT					
							
	Header						
		Name	Type	Mandatory	Example	Detail	Description
							
	PARAM						
		Name	Type	Mandatory	Example	Detail	Description
		cardId	String	Y	1		1번 카드 불러옴
							
	BODY						
		Name	Type	Mandatory	Example	Detail	Description
	PutCardData	title	String	Y	title2		카드 제목
		author	String	Y	park		작성자
		content	String	Y	content2		카드 내용
							
	Response Parameters						
		Name	Type	Mandatory	Example	Detail	Description
		isSuccess	Boolean	Y	TRUE		수정 성공 여부
		id	Long	Y	1		수정한 카드 id
		message	String	Y	수정 성공		수정 성공 메세지
		code	Int	Y	200		응답 코드
							
							
	Result Code						
		Code	message				
		200	카드 수정에 성공하였습니다.				
		400	카드 제목을 입력해 주세요.				
		400	카드 내용을 입력해 주세요.				
		400	작성자를 입력해 주세요.				

  
