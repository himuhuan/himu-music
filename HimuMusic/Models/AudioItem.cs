

using System.Text.Json.Serialization;
using HimuMusic.Helpers;

namespace HimuMusic.Models
{
    public class AudioItem
    {
        public long Id { get; set; }
        public string Title { get; set; } = string.Empty;
        public string Source { get; set; } = string.Empty;
        public string FirstPerformer { get; set; } = string.Empty;
        public string AlbumPictureBase64 { get; set; } = string.Empty;

        [JsonConverter(typeof(TimeSpanJsonConverter))]
        public TimeSpan Duration { get; set; }
        
        public long IssuerId { get; set; }

        public static AudioItem? ParseFromFile(string filePath)
        {
            if (File.Exists(filePath))
            {
                AudioItem audioItem = new();
                var info = TagLib.File.Create(filePath);
                audioItem.Title = info.Tag.Title;
                var pictures = info.Tag.Pictures;
                if (pictures.Length > 0)
                {
                    audioItem.AlbumPictureBase64 = Convert.ToBase64String(pictures[0].Data.Data);
                }
                audioItem.FirstPerformer = info.Tag.FirstPerformer ?? "Unknown Artist";
                audioItem.Duration = info.Properties.Duration;
                audioItem.Source = filePath;
                return audioItem;
            }
            return null;
        }
    }
}
