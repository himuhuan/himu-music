using HimuMusic.Models;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using BlazorComponent;

namespace HimuMusic.States
{
    public class MusicPlayerState : INotifyPropertyChanged
    {
        public string CurrentNavTitle { get; set; } = "Himu Music";
        public long CurrentLoginUserId { get; set; } = 0;
        public List<AudioItem>? CurrentPlayingList { get; set; } = null;

        public StringNumber CurrentPlayingListIndex { get; set; } = new(0);
        public AudioItem? PlayingMusic => CurrentPlayingList?[CurrentPlayingListIndex.ToInt32()];
        public HimuMusicUser? CurrentUser { get; set; } = default;
        public int CurrentPosition { get; set; }
        public bool IsPlaying { get; set; } = false;
        public bool ShowMusicPanel { get; set; } = true;

        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string? propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        protected bool SetField<T>(ref T field, T value, [CallerMemberName] string? propertyName = null)
        {
            if (EqualityComparer<T>.Default.Equals(field, value)) return false;
            field = value;
            OnPropertyChanged(propertyName);
            return true;
        }
    }
}
