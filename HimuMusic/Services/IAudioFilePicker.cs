using HimuMusic.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HimuMusic.Services
{
    public interface IAudioFilePicker
    {
        Task<AudioItem?> PickAudioFileAsync(string pickerTitle);
    }
}
