namespace HimuMusic.Models;

public class HimuMusicUser
{
    public long Id { get; set; }

    public string Name { get; set; } = string.Empty;

    public string Password { get; set; } = string.Empty;
    
    public string AvatarBase64 { get; set; } = string.Empty;

    public string AccessToken { get; set; } = string.Empty;
}