## ✨ flow-main

MSA 구조에서 메인으로 제공되는 기능(ex 로그인)들을 담당하는 Repository입니다.<br>
<br>

## Tool
- ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white)
- ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=spring&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white)
- ![Spring Event](https://img.shields.io/badge/Spring%20Event-6DB33F?style=flat-square&logo=spring&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)




## 🚀 주요 기능
```
1. 사용자 회원가입 및 회원탈퇴      # Spring Security BCryptPassword
2. 대학교 인증                  # UniCert 라이브러리
3. 사용자 로그인 / 로그아웃 t      # JWT를 생성 / 삭제 후 DB에 저장하는 방식 사용
4. 사용자 정보 확인 및 수정        
5. 포스트 작성, 수정, 삭제, 검색    # 검색 : 가장 많이 포함되어 있는 포스트부터 상단에 위치.
6. 트랜딩 포스트                 # 매일 00시 업데이트되며 댓글, 답글이 가장 많은 순서대로 순위. 
7. 포스트 좋아요 / 취소           
8. 댓글 및 답글                 # Depth 2로 설정
9. 팔로우 및 팔로잉
10. 알람 기능                   # Spring Event를 사용하여 사용자 액션 추적
```

## 🌊 CI/CD
```
.
├── .github
│   └── workflows
│       ├── ci-cd-dev.yml // dev 브랜치 전용 CI/CD
│       └── ci-cd-prd.yml // prd(main) 전용 CI/CD

1. Github Actions 실행
2. Docker 이미지 빌드
3. Private Docker Registry에 저장
4. flow-manifest 이미지 코드 수정
5. ArgoCD 최신화
```

