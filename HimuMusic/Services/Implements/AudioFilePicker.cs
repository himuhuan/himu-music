using HimuMusic.Models;

namespace HimuMusic.Services.Implements
{
    public class AudioFilePicker : IAudioFilePicker
    {
        private readonly static FilePickerFileType AudioFileType = new FilePickerFileType(
            new Dictionary<DevicePlatform, IEnumerable<string>>
            {
                { DevicePlatform.Android, new[] { "audio/mpeg" } },
                { DevicePlatform.WinUI, new[] { ".mp3" } }
            });

        public async Task<AudioItem?> PickAudioFileAsync(string pickerTitle)
        {
            PickOptions pickOptions = new()
            {
                PickerTitle = pickerTitle,
                FileTypes = AudioFileType
            };
            var result = await FilePicker.PickAsync(pickOptions);
            if (result != null)
            {
                return AudioItem.ParseFromFile(result.FullPath);
            }
            return null;
        }
    }
}
