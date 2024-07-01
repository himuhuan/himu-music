using HimuMusic.Models;

namespace HimuMusic.Services
{
    public interface IAudioPlayer
    {
        TimeSpan GetCurrentPosition();
        void Play(AudioItem audio);
        void Pause();
        void Stop();
        bool IsPlaying();
        void SeekTo(TimeSpan position);
        public void SeekTo(int positionSec);
    }
}
