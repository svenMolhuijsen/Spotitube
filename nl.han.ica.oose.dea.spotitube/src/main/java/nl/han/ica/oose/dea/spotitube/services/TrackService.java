package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackRequestDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackOverviewDto;
import nl.han.ica.oose.dea.spotitube.controllers.dto.TrackResponseDto;
import nl.han.ica.oose.dea.spotitube.datasources.TrackDAO;
import nl.han.ica.oose.dea.spotitube.models.TrackModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService implements ItrackService {

    private TrackDAO trackDao;
    private TokenService tokenService;

    public TrackService() {}

    @Inject
    public TrackService( TrackDAO trackDAO, TokenService tokenService) {
        this.trackDao = trackDAO;
        this.tokenService = tokenService;
    }

    @Override
    public TrackOverviewDto getTracksForPlaylist(String token, int playlistId) {
         {
             tokenService.checkToken(token);
             List<TrackModel> trackModels = trackDao.getPlaylistTracks(playlistId);
             TrackOverviewDto trackOverviewDto = getTrackOverviewDto(trackModels);
             return trackOverviewDto;
             }
    }

    @Override
    public TrackOverviewDto deleteTrackInPlaylist(String token, int playlistId, int trackId) {
        tokenService.checkToken(token, playlistId);

        trackDao.deletePlaylistTrack(playlistId, trackId);
            return getTracksForPlaylist(token, playlistId);    }

    @Override
    public TrackOverviewDto addTrackToPlaylist(String token, int playlistId, TrackRequestDto trackRequestDto) {
        tokenService.checkToken(token, playlistId);
        TrackModel trackModel = new TrackModel();
        trackModel.setId(trackRequestDto.getId());
        trackModel.setOfflineAvailable(trackRequestDto.getOfflineAvailable());
        trackDao.addPlayListTrack(trackModel, playlistId, token);
        return getTracksForPlaylist(token, playlistId);
    }

    @Override
    public TrackOverviewDto getAvailableTracksForPlaylist(String token, int playlistId) {
        tokenService.checkToken(token, playlistId);
        List<TrackModel> trackModels = trackDao.getAvailableTracks(playlistId);
        TrackOverviewDto trackOverviewDto = getTrackOverviewDto(trackModels);
        return trackOverviewDto;
    }

        private TrackOverviewDto getTrackOverviewDto(List<TrackModel> trackModels) {
            TrackOverviewDto response = new TrackOverviewDto();
            response.setTracks(convertToDtoList(trackModels));
            return response;
        }

        private List<TrackResponseDto> convertToDtoList(List<TrackModel> trackModels) {
            List<TrackResponseDto> trackResponseDtos = new ArrayList<>();
            for (TrackModel modelObject : trackModels) {
                trackResponseDtos.add(
                        new TrackResponseDto(
                                modelObject.getId(),
                                modelObject.getTitle(),
                                modelObject.getPerformer(),
                                modelObject.getDuration(),
                                modelObject.getAlbum(),
                                modelObject.getPlaycount(),
                                modelObject.getPublicationDate(),
                                modelObject.getDescription(),
                                modelObject.getOfflineAvailable()));
            }
            return trackResponseDtos;
        }
}
