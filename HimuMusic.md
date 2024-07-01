
## 部署

后端 MAVEN 编译后启动，监听 8080 接口。

客户端，请以 Visual Studio 启动后, 确保您已经安装：

- .NET MAUI 开发
-  Visual Studio 菜单栏 -> 工具 -> Android ->  Android设备管理器 -> 新建 Android 虚拟机

在启动向选中虚拟机，

![[Pasted image 20240701115132.png]]

点击该按钮即可完成编译启动，并在虚拟机启动。

> 发布 APK 详情参见：[发布用于临时分发的 .NET MAUI Android 应用 - .NET MAUI | Microsoft Learn](https://learn.microsoft.com/zh-cn/dotnet/maui/android/deployment/publish-ad-hoc?view=net-maui-8.0)

要生成 Android APK， 请将解决方案配置调为 Release，

![[Pasted image 20240701115402.png]]

右键项目 -> 发布, 即可开始生成 APK, 之后需要将生成好的 APK 根据自身创建的签名进行分发，才可以在其他设备中安装。

![[Pasted image 20240701115628.png]]


# 后端

Java Spring Boot

## DDL

![[Pasted image 20240625124652.png]]

```MySQL
create table if not exists himu_music.musics  
(  
    Id                 bigint auto_increment  primary key,  
    Title              varchar(200) not null,
    Source              varchar(200) not null,  
    FirstPerformer     varchar(200) not null,  
    AlbumPictureBase64 mediumtext   not null,  
    Duration           time(6)      not null,  
    IssuerId           bigint       not null,  
    constraint FK_musics_users_IssuerId  
        foreign key (IssuerId) references himu_music.users (Id)  
            on delete cascade  
);  
  
create index IX_musics_IssuerId  
    on himu_music.musics (IssuerId);  
  
create index IX_musics_Title  
    on himu_music.musics (Title);
```

```mysql
create table if not exists himu_music.users
(
    Id           bigint auto_increment primary key,
    Name         varchar(50) not null,
    Password     varchar(50) not null,
    AvatarBase64 mediumtext  not null,
    constraint IX_users_Name unique (Name)
);
```

```mysql
create table if not exists himu_music.user_favorites
(
    FavoritesId     bigint not null,
    HimuMusicUserId bigint not null,
    primary key (FavoritesId, HimuMusicUserId),
    constraint FK_user_favorites_musics_FavoritesId
        foreign key (FavoritesId) references himu_music.musics (Id)
            on delete cascade,
    constraint FK_user_favorites_users_HimuMusicUserId
        foreign key (HimuMusicUserId) references himu_music.users (Id)
            on delete cascade
);

create index IX_user_favorites_HimuMusicUserId
    on himu_music.user_favorites (HimuMusicUserId);
```

```mysql
create table himu_music.user_recent_play
(
    HimuMusicUser1Id bigint not null,
    RecentPlayId     bigint not null,
    primary key (HimuMusicUser1Id, RecentPlayId),
    constraint FK_user_recent_play_musics_RecentPlayId
        foreign key (RecentPlayId) references himu_music.musics (Id)
            on delete cascade,
    constraint FK_user_recent_play_users_HimuMusicUser1Id
        foreign key (HimuMusicUser1Id) references himu_music.users (Id)
            on delete cascade
);

create index IX_user_recent_play_RecentPlayId
    on himu_music.user_recent_play (RecentPlayId);
```

## 接口说明

所有 API 都只能直接返回结果本身，不能含有其他额外信息。

当 操作完成时，你应该只设置Http 操作码代表你的操作是否成功（注意，不应该在你的回复报文内）。此时，Result 不应该含有任何值。除此之外，你还应该设具体细节见以下API 说明。

以 *  标注说明需要检查令牌。令牌无效或不存在一律返回 401
### 注册用户 POST /api/user 

#### 请求

```json
{
  "id": 0,
  "name": "string",
  "password": "string",
  "avatarBase64": "string"
}
```

请求中，id总是0，具体id由后端决定。
#### 回复

`200 Ok` 此操作成功完成: 返回创建好的用户Id。
`400 BadRequest` 密码不一致或该用户已存在

### 登录 POST /api/user/authenticate

#### 请求

```json
{
  "userName": "string",
  "password": "string"
}
```

#### 回复

`200 Ok` 此操作成功完成: 返回用户令牌。
`400 BadRequest` 密码不一致
`404 NotFound` 该用户不存在

### 获取用户信息 GET /api/user/{userId}

#### 请求

```
https://localhost:7154/api/user/12345678
```

#### 回复

`200 Ok` 此操作成功完成: 返回

```json
{
  "id": 0,
  "name": "string",
  "password": "string",
  "avatarBase64": "string"
}
```

`404 NotFound` 该用户不存在

### 更新用户信息 PUT /api/user/{userId} *

#### 请求

```
{
  "id": 0,
  "name": "string",
  "password": "string",
  "avatarBase64": "string"
}
```

#### 回复

`200 Ok` 此操作成功完成。
`400 BadRequest` 报文id与请求 URL 不符，或者其他错误
`401 Unauthorized` 用户未登录 

  
### 上传本地音乐 POST /api/music *

#### 请求

请求以 Form 形式发出：

```java
public class UploadMusicRequest {
    private String title;
    private String firstPerformer;
    private String albumPictureBase64;
    private Duration duration;
    private long issuerId;
    private MultipartFile musicFile;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPerformer() {
        return firstPerformer;
    }

    public void setFirstPerformer(String firstPerformer) {
        this.firstPerformer = firstPerformer;
    }

    public String getAlbumPictureBase64() {
        return albumPictureBase64;
    }

    public void setAlbumPictureBase64(String albumPictureBase64) {
        this.albumPictureBase64 = albumPictureBase64;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(long issuerId) {
        this.issuerId = issuerId;
    }

    public MultipartFile getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(MultipartFile musicFile) {
        this.musicFile = musicFile;
    }
}

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @PostMapping
    public ResponseEntity<Void> uploadMusic(@ModelAttribute UploadMusicRequest request) {
        // 在这里处理上传逻辑
        return ResponseEntity.ok().build();
    }
}
```

#### 回复

`200` -- OK
`400 `-- 发生错误
`401 Unauthorized` 用户未登录 

### 搜索音乐 POST /api/music/search

#### 请求

query 形式：

```bash
api/music/search?query=musicName
```

#### 回复

没有结果时 204

```
[
  {
    "id": 0,
    "title": "string",
    "firstPerformer": "string",
    "albumPictureBase64": "string",
    "duration": 256700,
    "issuerId": 0
  },
  {
    "id": 1,
    "title": "string",
    "firstPerformer": "string",
    "albumPictureBase64": "string",
    // 时长一律用秒数表示
    "duration": 256700,
    "issuerId": 0
  },
]
```


以下是根据更新后的 `MusicItem` 数据模型重新生成的文档：

### 添加用户收藏 POST /api/favorite

#### 请求

```
https://localhost:7154/api/favorite
```

#### 请求体

```json
{
  "userId": 12345678,
  "musicId": 87654321
}
```

#### 回复

`200 Ok` 此操作成功完成:

`400 BadRequest` 请求无效

---
### 获取用户收藏 GET /api/favorite

#### 请求

```
https://localhost:7154/api/favorite
```

#### 回复

`200 Ok` 此操作成功完成: 返回

```json
[
  {
    "id": 0,
    "title": "string",
    "firstPerformer": "string",
    "albumPictureBase64": "string",
    // 时长一律用秒数表示
    "duration": 256700,
    "issuerId": 0
  }
]
```

`204 No Content` 没有找到用户收藏的音乐

---

### 添加用户最近播放 POST /api/recent_play

#### 请求

```
https://localhost:7154/api/recent_play
```

#### 请求体

```json
{
  "userId": 12345678,
  "musicId": 87654321
}
```

#### 回复

`200 Ok` 此操作成功完成
`400 BadRequest` 请求无效

---

### 获取用户最近播放 GET /api/recent_play

#### 请求

```
https://localhost:7154/api/recent_play
```

#### 回复

`200 Ok` 此操作成功完成

```json
[
  {
    "id": 0,
    "title": "string",
    "firstPerformer": "string",
    "albumPictureBase64": "string",
    // 时长一律用秒数表示
    "duration": 256700,
    "issuerId": 0
  }
]

```

`404 NotFound`
